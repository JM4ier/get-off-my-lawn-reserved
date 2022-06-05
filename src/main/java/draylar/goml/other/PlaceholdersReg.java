package draylar.goml.other;

import com.mojang.authlib.GameProfile;
import draylar.goml.GetOffMyLawn;
import draylar.goml.api.ClaimUtils;
import eu.pb4.placeholders.api.PlaceholderResult;
import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.placeholders.api.TextParserUtils;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.stream.Collectors;

@ApiStatus.Internal
public class PlaceholdersReg {
    public static void init() {
        Placeholders.register(new Identifier("goml", "claim_owners"), (ctx, arg) -> {
            if (!ctx.hasPlayer()) {
                return PlaceholderResult.invalid("No player!");
            }

            Text wildnessText = GetOffMyLawn.CONFIG.placeholderNoClaimOwners.text();
            if (arg != null) {
                wildnessText = TextParserUtils.formatText(arg);
            }

            var claims = ClaimUtils.getClaimsAt(ctx.player().getWorld(), ctx.player().getBlockPos()).collect(Collectors.toList());

            if (claims.size() == 0) {
                return PlaceholderResult.value(wildnessText);
            } else {
                var claim = claims.get(0);

                List<String> owners = new ArrayList<>();
                for (UUID owner : claim.getValue().getOwners()) {
                    Optional<GameProfile> profile = ctx.server().getUserCache().getByUuid(owner);

                    if (profile.isPresent()) {
                        owners.add(profile.get().getName());
                    }
                }


                return PlaceholderResult.value(owners.size() > 0 ? Text.literal(String.join(", ", owners)) : wildnessText);
            }
        });

        Placeholders.register(new Identifier("goml", "claim_owners"), (ctx, arg) -> {
            if (!ctx.hasPlayer()) {
                return PlaceholderResult.invalid("No player!");
            }

            Text wildnessText = GetOffMyLawn.CONFIG.placeholderNoClaimOwners.text();
            if (arg != null) {
                wildnessText = TextParserUtils.formatText(arg);
            }

            var claims = ClaimUtils.getClaimsAt(ctx.player().getWorld(), ctx.player().getBlockPos()).collect(Collectors.toList());

            if (claims.size() == 0) {
                return PlaceholderResult.value(wildnessText);
            } else {
                var claim = claims.get(0);

                List<String> owners = new ArrayList<>();
                for (UUID owner : claim.getValue().getOwners()) {
                    Optional<GameProfile> profile = ctx.server().getUserCache().getByUuid(owner);

                    if (profile.isPresent()) {
                        owners.add(profile.get().getId().toString());
                    }
                }


                return PlaceholderResult.value(owners.size() > 0 ? Text.literal(String.join(", ", owners)) : wildnessText);
            }
        });

        Placeholders.register(new Identifier("goml", "claim_trusted"), (ctx, arg) -> {
            if (!ctx.hasPlayer()) {
                return PlaceholderResult.invalid("No player!");
            }


            Text wildnessText = GetOffMyLawn.CONFIG.placeholderNoClaimTrusted.text();
            if (arg != null) {
                wildnessText = TextParserUtils.formatText(arg);
            }

            var claims = ClaimUtils.getClaimsAt(ctx.player().getWorld(), ctx.player().getBlockPos()).collect(Collectors.toList());

            if (claims.size() == 0) {
                return PlaceholderResult.value(wildnessText);
            } else {
                var claim = claims.get(0);

                List<String> trusted = new ArrayList<>();
                for (UUID owner : claim.getValue().getTrusted()) {
                    Optional<GameProfile> profile = ctx.server().getUserCache().getByUuid(owner);

                    if (profile.isPresent()) {
                        trusted.add(profile.get().getName());
                    }
                }


                return PlaceholderResult.value(trusted.size() > 0 ? Text.literal(String.join(", ", trusted)) : wildnessText);
            }
        });

        Placeholders.register(new Identifier("goml", "claim_trusted_uuid"), (ctx, arg) -> {
            if (!ctx.hasPlayer()) {
                return PlaceholderResult.invalid("No player!");
            }


            Text wildnessText = GetOffMyLawn.CONFIG.placeholderNoClaimTrusted.text();
            if (arg != null) {
                wildnessText = TextParserUtils.formatText(arg);
            }

            var claims = ClaimUtils.getClaimsAt(ctx.player().getWorld(), ctx.player().getBlockPos()).collect(Collectors.toList());

            if (claims.size() == 0) {
                return PlaceholderResult.value(wildnessText);
            } else {
                var claim = claims.get(0);

                List<String> trusted = new ArrayList<>();
                for (UUID owner : claim.getValue().getTrusted()) {
                    Optional<GameProfile> profile = ctx.server().getUserCache().getByUuid(owner);

                    if (profile.isPresent()) {
                        trusted.add(profile.get().getId().toString());
                    }
                }


                return PlaceholderResult.value(trusted.size() > 0 ? Text.literal(String.join(", ", trusted)) : wildnessText);
            }
        });

        Placeholders.register(new Identifier("goml", "claim_info"), (ctx, arg) -> {
            if (!ctx.hasPlayer()) {
                return PlaceholderResult.invalid("No player!");
            }


            var wildnessText = GetOffMyLawn.CONFIG.placeholderNoClaimInfo.text();
            var canBuildText = GetOffMyLawn.CONFIG.placeholderClaimCanBuildInfo.node();
            var cantBuildText = GetOffMyLawn.CONFIG.placeholderClaimCantBuildInfo.node();

            if (arg != null) {
                String[] texts = arg.replace("\\:", "&bslsh\001;").split(":");

                if (texts.length > 0) {
                    wildnessText = TextParserUtils.formatText(texts[0].replace("&bslsh;\001", ":"));
                }
                if (texts.length > 1) {
                    canBuildText = TextParserUtils.formatNodes(texts[1].replace("&bslsh;\001", ":"));
                }
                if (texts.length > 2) {
                    cantBuildText = TextParserUtils.formatNodes(texts[2].replace("&bslsh;\001", ":"));
                }
            }

            var claims = ClaimUtils.getClaimsAt(ctx.player().getWorld(), ctx.player().getBlockPos()).collect(Collectors.toList());


            if (claims.size() == 0) {
                return PlaceholderResult.value(wildnessText);
            } else {
                var claim = claims.get(0);

                List<String> owners = new ArrayList<>();
                List<String> ownersUuid = new ArrayList<>();

                for (UUID owner : claim.getValue().getOwners()) {
                    Optional<GameProfile> profile = ctx.server().getUserCache().getByUuid(owner);

                    if (profile.isPresent()) {
                        owners.add(profile.get().getName());
                        ownersUuid.add(profile.get().getId().toString());
                    }
                }
                List<String> trusted = new ArrayList<>();
                List<String> trustedUuid = new ArrayList<>();
                for (UUID owner : claim.getValue().getTrusted()) {
                    Optional<GameProfile> profile = ctx.server().getUserCache().getByUuid(owner);

                    if (profile.isPresent()) {
                        trusted.add(profile.get().getName());
                        trustedUuid.add(profile.get().getId().toString());
                    }
                }


                return PlaceholderResult.value(Placeholders.parseText(
                        claim.getValue().hasPermission(ctx.player()) ? canBuildText : cantBuildText,
                        Placeholders.PREDEFINED_PLACEHOLDER_PATTERN,
                        Map.of("owners", Text.literal(String.join(", ", owners)),
                                "owners_uuid", Text.literal(String.join(", ", ownersUuid)),
                                "trusted", Text.literal(String.join(", ", trusted)),
                                "trusted_uuid", Text.literal(String.join(", ", trustedUuid)),
                                "anchor", Text.literal(claim.getValue().getOrigin().toShortString())
                        )));
            }
        });
    }
}